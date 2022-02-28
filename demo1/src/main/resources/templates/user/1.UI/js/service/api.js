export default class Api {
    /**
     * fetch API method get with param url
     * Author: nlanh
     */
    getAPI(url){
        // return fetch(url, {
        //     method: "GET",
        //     headers: {
        //         "Content-Type": "application/json"
        //     },
        //     'credentials': 'same-origin'     
        // }).then(
        //     response => {
        //         return response.json().then(
        //             (data) => {
        //                 return data;
        //             }
        //         )
        //     },
        //     error => {
        //         console.log(error);
        //         return false;
        //     }
        // )

        return $.ajax({
            type: "GET",
            url: url,
            success: function (response) {
                return response;
            }
        });
    }

    /**
     * fetch API method post with param "url" and request "data"
     * Author: nlanh
     */
    postAPI(url, data){
        // return fetch(url, {
        //     method: "POST",
        //     headers: {
        //         "Content-Type": "application/json"
        //     },
        //     'credentials': 'same-origin',
        //     body: data
        // }).then(
        //     response => {
        //         return response.json(
        //             data => {
        //                 return data;
        //             }
        //         )
        //     }, 
        //     error => {
        //         console.log(error);
        //         return false;
        //     }
        // )

        return $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log(response);
                return response;
            }
        });
    }
}
