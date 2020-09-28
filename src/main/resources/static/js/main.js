var orderApi = Vue.resource('/order{/userId}');

Vue.component('order-form', {
    data: function () {
        return {
            text: '',
            userId: '',
            password: ''
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Order here" v-model="text"/><br>' +
        '<input type="text" placeholder="Your id" v-model="userId"/><br>' +
        '<input type="password" placeholder="Password" v-model="password"/><br>' +
        '<input type="button" value="Place Order" @click="save"/>'
        + '</div>',
    methods: {
        save: function () {
            if (this.text === '' || this.userId === '' || this.password === '') {
                return;
            }
            var object = {
                text: this.text,
                password: this.password
            };
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/order/' + this.userId, true);
            xhr.setRequestHeader('Content-Type', 'application/json')
            xhr.send('{"text": "' + object.text + '",\n'+
                '"password": "' + object.password +
                '"}');

            this.text = '';
            this.userId = '';
            this.password = '';

        }
    }
});

Vue.component('orders-list', {
    template:
        '<div>' +
        '<order-form/>' +
        '</div>'
});

var app = new Vue({
    el: '#app',
    template: '<orders-list/>'
});