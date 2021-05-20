import AxiosClient from "./clients/AxiosClient";


export const OrderService = {
    getOrders,
    getSellerComments,
    addOrder,
    addCartItem,
    putOrder,
};

async function getOrders(username) {
    return await AxiosClient.get("http://localhost:8080/getBuyerOrders/" + username);
}

async function getSellerComments(username) {
    return await AxiosClient.get("http://localhost:8080/sellerComments/" + username);
}

async function addOrder(order) {
    return await AxiosClient.post("http://localhost:8080/postOrder", order);
}

async function addCartItem(cartItem) {
    return await AxiosClient.post("http://localhost:8080/postCartItem", cartItem);
}

async function putOrder(order) {
    return await AxiosClient.put("http://localhost:8080/updateOrder", order);
}
