const $ = (id) => document.getElementById(id);
const api = async (url, options) => {
  const response = await fetch(url, options);
  if (!response.ok)
    throw new Error(
      (await response.json().catch(() => ({}))).message || "Request failed",
    );
  return response.json();
};
const esc = (value) => {
  const n = document.createElement("span");
  n.textContent = value;
  return n.innerHTML;
};
async function metrics() {
  const data = await api("/api/metrics");
  $("documents").textContent = data.documents;
  $("terms").textContent = data.terms;
  $("cache").textContent = data.cacheEntries;
  $("shardBars").innerHTML =
    '<span class="active"></span><span class="active"></span><span class="active"></span><span class="active"></span>';
}
function mark(text, terms) {
  const safe = esc(text);
  const source = terms
    .map((t) => t.replace(/[.*+?^${}()|[\]\\]/g, "\\$&"))
    .join("|");
  return source
    ? safe.replace(new RegExp(`(${source})`, "gi"), "<mark>$1</mark>")
    : safe;
}
function display(response) {
  $("latency").textContent = `${response.latencyMs.toFixed(1)} ms`;
  $("title").textContent = response.results.length
    ? `${response.results.length} ranked result${response.results.length === 1 ? "" : "s"}`
    : "No matching documents";
  $("meta").textContent = response.cacheHit
    ? "CACHE HIT · returned saved Top-K"
    : "LOCAL INDEX · BM25 · TOP-K";
  if (!response.results.length) {
    $("cards").innerHTML =
      '<div class="empty"><span>⌁</span><h3>No matching documents</h3><p>Try a different phrase or add relevant documents.</p></div>';
    return;
  }
  $("cards").innerHTML = response.results
    .map(
      (r, index) =>
        `<article class="card"><div><span class="pill">LOCAL INDEX</span><span class="score">#${index + 1} · BM25 ${r.score.toFixed(3)}</span></div><h3>${esc(r.title)}</h3><p>${mark(r.snippet, r.matchedTerms)}</p><footer><span>${r.matchedTerms.length} matching term${r.matchedTerms.length === 1 ? "" : "s"}</span><button data-id="${r.documentId}">Open document →</button></footer></article>`,
    )
    .join("");
  document
    .querySelectorAll("[data-id]")
    .forEach(
      (button) => (button.onclick = () => openDocument(button.dataset.id)),
    );
}
async function runSearch() {
  const q = $("query").value.trim();
  if (!q) return;
  try {
    display(await api(`/api/search?q=${encodeURIComponent(q)}`));
    await metrics();
  } catch (error) {
    $("title").textContent = error.message;
  }
}
async function openDocument(id) {
  const doc = await api(`/api/documents/${id}`);
  $("docTitle").textContent = doc.title;
  $("docMeta").textContent =
    `LOCAL INDEX · ${doc.terms.length.toLocaleString()} TOKENS · ${doc.mediaType}`;
  $("docBody").textContent = doc.content;
  $("viewer").showModal();
}
async function upload(files) {
  if (!files.length) return;
  const form = new FormData();
  [...files].forEach((file) => form.append("files", file));
  $("ingestStatus").textContent =
    `Indexing ${files.length} file${files.length === 1 ? "" : "s"}…`;
  try {
    const added = await api("/api/documents", { method: "POST", body: form });
    $("ingestStatus").textContent =
      `${added.length} file${added.length === 1 ? "" : "s"} indexed; cache cleared`;
    await metrics();
    if ($("query").value.trim()) runSearch();
  } catch (error) {
    $("ingestStatus").textContent = `Upload failed: ${error.message}`;
  }
}
$("searchForm").addEventListener("submit", (event) => {
  event.preventDefault();
  runSearch();
});
$("files").addEventListener("change", (event) => upload(event.target.files));
["dragenter", "dragover"].forEach((type) =>
  $("dropzone").addEventListener(type, (event) => {
    event.preventDefault();
    $("dropzone").classList.add("drag");
  }),
);
["dragleave", "drop"].forEach((type) =>
  $("dropzone").addEventListener(type, (event) => {
    event.preventDefault();
    $("dropzone").classList.remove("drag");
  }),
);
$("dropzone").addEventListener("drop", (event) =>
  upload(event.dataTransfer.files),
);
$("close").onclick = () => $("viewer").close();
metrics();
