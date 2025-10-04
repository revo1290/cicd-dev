<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <v-app-bar-title>My App</v-app-bar-title>
      <v-spacer></v-spacer>
      <v-btn text to="/login">Login</v-btn>
      <v-btn text to="/register">Register</v-btn>
      <v-btn text to="/home">Home</v-btn>
    </v-app-bar>

    <v-main>
      <v-container fluid>
        <router-view />
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
/* グローバルなスタイルをここに記述 */
</style>
