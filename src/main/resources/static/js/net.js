class Net {

    xhr

    constructor() {
        this.xhr = new XMLHttpRequest()
    }

    getCookie(name) {
        let matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        return matches ? decodeURIComponent(matches[1]) : undefined;
    }

    postRequest(url, body, response) {
        let request = this.xhr

        request.open('POST', url, true)
        request.setRequestHeader("Content-Type", 'application/json')
        request.setRequestHeader("X-XSRF-TOKEN", this.getCookie('XSRF-TOKEN'))
        request.send(body)

        request.onreadystatechange = function () {
            if (request.readyState === XMLHttpRequest.DONE) {
                if (request.status + "".match(new RegExp(/(^[45])\d*/g)) !== null) {
                    let responseMsg = JSON.parse(request.responseText)
                    alert('Action can\'t be provided cause: ' + responseMsg.status + ' ' + responseMsg.error)
                    if (request.status === 403)
                        window.location.replace('/')
                    return
                }
                let responseJSON = JSON.parse(request.responseText)
                if (response != null)
                    response.push(responseJSON)
            }
        }
    }

}