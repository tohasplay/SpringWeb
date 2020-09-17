var registerApi = Vue.resource('/customer{/id}');

Vue.component('register-form', {
    props: ['customers'],
    data: function () {
        return {
            name: ''
        }
    },
    template: '<div>' +
        '<input type="text" placeholder="Your name" v-model="name"/><br>' +
        '<input type="button" value="Register" @click="save"/>'
        + '</div>',
    methods: {
        save: function () {
            if (this.name === '') {
                return;
            }
            var customer = {name: this.name};
            registerApi.save({}, customer).then(result =>
                result.json().then(data => {
                    this.customers.push(data);
                    this.name = '';
                }))
        }
    }

})

Vue.component('customer-row', {
    props: ['customer'],
    template: '<div> <i>( ur id: {{ customer.id }})</i> {{ customer.name }} registered successfully!</div>'
});

Vue.component('customers-list', {
    props: ['customers'],
    template:
        '<div>' +
        '<register-form :customers="customers"/>' +
        '<customer-row v-for="customer in customers" :key="customer.id" :customer="customer"/></div>'
});


var app = new Vue({
    el: '#app',
    template: '<customers-list :customers="customers"/>',
    data: {
        customers: []
    }
});