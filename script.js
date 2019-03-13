(function () {
    var versions = [];
    
    for (var i = 0; i < versions.length; i++) {
        var version = versions[i];
        var row = document.createElement("tr");
        var link_html = document.createElement("a");
        var link_zip  = document.createElement("a");
        link_html.href = "kotlin3_" + version;
        link_html.innerText = "kotlin3_" + version;
        link_zip.href = "kotlin3_" + version + "-docs.zip";
        link_zip.innerText = "(Download)";
        row.innerHTML = link_html.outerHTML + " " + link_zip.outerHTML;
        document.querySelector("tbody").appendChild(row);
    }
})();
