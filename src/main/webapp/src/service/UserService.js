import AxiosClient from "./clients/AxiosClient";


export const UserService = {
    registerBuyer,
    registerSeller,
    editUser,
    getUser,
    getUsers,
    getSellers,
    getSellerGrade,
    changePassword
};

async function registerBuyer() {
    return await AxiosClient.post("http://localhost:8080/buyer/register");
}

async function registerSeller() {
    return await AxiosClient.post("http://localhost:8080/seller/register");
}

async function getUser(username) {
    return await AxiosClient.get("http://localhost:8080/user/" + username);
}

async function getUsers() {
    return await AxiosClient.get("http://localhost:8080/users");
}

async function getSellers() {
    return await AxiosClient.get("http://localhost:8080/sellers");
}

async function getSellerGrade(seller) {
    return await AxiosClient.get("http://localhost:8080/seller/grade/" + seller);
}

async function editUser(user) {
    return await AxiosClient.put("http://localhost:8080/user/edit", user);
}

async function changePassword(userPasswordChange) {
    console.log(userPasswordChange)
    return await AxiosClient.put("http://localhost:8080/user/changePass/" + userPasswordChange.username, userPasswordChange);
}