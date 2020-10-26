let net = new Net();

Vue.component('register-form', {
    props: ['customers'],
    data: function () {
        return {
            name: '',
            mail: '',
            phone: '',
            password: ''
        }
    },
    template: '<div>' +
        '<input type="text" placeholder="Your name" v-model="name"/><br>' +
        '<input type="text" placeholder="e-mail" v-model="mail"/><br>' +
        '<input type="text" placeholder="phone" v-model="phone"/><br>' +
        '<input type="password" placeholder="password" v-model="password"/><br>' +
        '<input type="button" value="Register" @click="save"/>'
        + '</div>',
    methods: {
        save: function () {
            if (this.name === '' || this.mail === '' || this.phone === '' || this.password === '') {
                return;
            }
            let customer = {
                name: this.name,
                mail: this.mail,
                phone: this.phone,
                password: this.password
            }

            let body = JSON.stringify({
                name: customer.name,
                mail: customer.mail,
                phone: customer.phone,
                password: customer.password
            })

            this.name=''
            this.mail=''
            this.phone = ''
            this.password=''

            net.postRequest('/customer', body, this.customers)
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