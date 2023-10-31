function isLoggedIn(){
    return !(sessionStorage.getItem("uuid") == null);
}