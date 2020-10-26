let net = new Net()

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

            let body = JSON.stringify({text: this.text, password: this.password})

            net.postRequest('/order/' + this.userId, body)

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