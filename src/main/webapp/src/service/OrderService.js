import AxiosClient from "./clients/AxiosClient";


export const OrderService = {
    addOrder,
    addCartItem,
};

async function addOrder(order) {
    return await AxiosClient.post("http://localhost:8080/postOrder", order);
}

async function addCartItem(cartItem) {
    return await AxiosClient.post("http://localhost:8080/postCartItem", cartItem);
}
