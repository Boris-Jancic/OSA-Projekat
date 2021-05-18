import AxiosClient from "./clients/AxiosClient";

export const ArticleService = {
    addArticle,
    getArticles,
    editArticle,
    deleteArticle,
    getSellerArticles
};

async function addArticle(article) {
    return await AxiosClient.post("http://localhost:8080/addArticle", article);
}

async function getArticles() {
    return await AxiosClient.get("http://localhost:8080/allArticles");
}

async function editArticle(article) {
    return await AxiosClient.put("http://localhost:8080/editArticle", article);
}

async function deleteArticle(id) {
    return await AxiosClient.delete('http://localhost:8080/deleteArticle/' + id);
}

async function getSellerArticles() {
    // return await AxiosClient.get("delete/:id");
}
