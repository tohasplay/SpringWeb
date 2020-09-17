var orderApi = Vue.resource('/order{/id}');

Vue.component('order-form', {
    data: function () {
        return {
            text: '',
            customer_id: ''
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Order here" v-model="text"/><br>' +
        '<input type="text" placeholder="Your id" v-model="customer_id"/><br>' +
        '<input type="button" value="Place Order" @click="save"/>'
        + '</div>',
    methods: {
        save: function () {
            if (this.text === '') {
                return;
            }
            var order = {
                text: this.text,
                customer_id: this.customer_id
            };
            orderApi.save({}, order)
            this.text = '';
            this.customer_id = '';

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