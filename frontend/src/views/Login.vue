<template>
  <v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
      <v-col cols="12" sm="8" md="4">
        <v-card class="elevation-12">
          <v-toolbar color="primary" dark flat>
            <v-toolbar-title>Login</v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-form @submit.prevent="login">
              <v-text-field
                label="Username"
                v-model="username"
                prepend-icon="mdi-account"
                type="text"
              ></v-text-field>
              <v-text-field
                label="Password"
                v-model="password"
                prepend-icon="mdi-lock"
                type="password"
              ></v-text-field>
              <v-alert v-if="error" type="error" dense>{{ error }}</v-alert>
            </v-form>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="login">Login</v-btn>
          </v-card-actions>
          <v-card-text class="text-center">
            <p>Don't have an account? <router-link to="/register">Register</router-link></p>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const username = ref('')
const password = ref('')
const error = ref(null)
const router = useRouter()

const login = async () => {
  error.value = null
  try {
    await axios.post('/api/login', 
      `username=${username.value}&password=${password.value}`,
      {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        withCredentials: true
      }
    );
    router.push('/home')
  } catch (err) {
    error.value = 'Invalid username or password'
    console.error('Login failed:', err)
  }
}
</script>