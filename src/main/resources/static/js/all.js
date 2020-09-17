var orderApi = Vue.resource('/order{/id}');

Vue.component('order-row', {
    props: ['order'],
    template: '<div> <i>({{order.id }})</i> {{ order.text }} | {{ order.price }}</div>'
});

Vue.component('orders-list', {
    props: ['orders'],
    template:
        '<div>' +
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