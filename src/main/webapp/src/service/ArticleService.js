import AxiosClient from "./clients/AxiosClient";

export const ArticleService = {
    addArticle,
    getArticle,
    getArticles,
    editArticle,
    deleteArticle,
    getSellerArticles
};

async function addArticle(article) {
    return await AxiosClient.post("http://localhost:8080/addArticle", article);
}

async function getArticle(id) {
    return await AxiosClient.get("http://localhost:8080/getArticle/" + id);
}

async function getArticles() {
    return await AxiosClient.get("http://localhost:8080/allArticles");
}

async function editArticle(article) {
    return await AxiosClient.put("http://localhost:8080/updateArticle", article);
}

async function deleteArticle(id) {
    return await AxiosClient.delete('http://localhost:8080/deleteArticle/' + id);
}

async function getSellerArticles(id) {
    return await AxiosClient.get("http://localhost:8080/sellerArticles/" + id);
}
