const countApi = Vue.resource('/customer/count/{id}');

Vue.component('total-form', {
    props: ['node'],
    data: function () {
        return {id: ''}
    },
    template: '<div>' +
        '<input type="text" placeholder="Your id" v-model="id"><br>' +
        '<input type="button" value="get total" @click="get"/>' +
        '<div>total price:{{node.price}} total count:{{node.total}}</div>' +
        '</div>',
    methods: {
        get: function () {
            if (this.id === '') {
                return;
            }
            this.node.price = 0;
            this.node.total = 0;
            countApi.get({id: this.id}).then(
                result => result.json().then(
                    data => {
                        this.node.price = data.first;
                        this.node.total = data.second;
                    }
                )
            )
        }
    }
})

const app = new Vue({
    el: '#app',
    template: '<total-form :node="node"/>',
    data: {
        node: {
            price: 0,
            total: 0
        }
    }
});