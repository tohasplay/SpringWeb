var orderApi = Vue.resource('/order{/id}');

Vue.component('order-form', {
    props: ['orders'],
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
            var order = {text: this.text,
                        customer_id: this.customer_id};
            orderApi.save({}, order).then(result =>
                result.json().then(data => {
                    this.orders.push(data);
                    this.text = '';
                    this.customer_id = '';
                }))
        }
    }
})

Vue.component('order-row', {
    props: ['order'],
    template: '<div> <i>({{order.id }})</i> {{ order.text }} | {{ order.price }}</div>'
});

Vue.component('orders-list', {
    props: ['orders'],
    template:
        '<div>' +
        '<order-form :orders="orders"/>' +
        '<order-row v-for="order in orders" :key="order.id" :order="order"/></div>',
    created: function () {
        orderApi.get().then(result =>
            result.json().then(data =>
                data.forEach(order => this.orders.push(order))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template: '<orders-list :orders="orders"/>',
    data: {
        orders: []
    }
});