<template>
  <div>
    <h1>Home</h1>
    <div v-if="user">
      <p>ID: {{ user.id }}</p>
      <p>Username: {{ user.username }}</p>
    </div>
    <div v-else-if="errorMessage">
      <p style="color: red;">{{ errorMessage }}</p>
    </div>
    <div v-else>
      <p>Loading user data...</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      user: null,
      errorMessage: null
    };
  },
  async created() {
    try {
      const response = await axios.get('/api/users/me');
      this.user = response.data;
    } catch (error) {
      console.error("Failed to fetch user data:", error);
      this.errorMessage = "Failed to load user data. Please try logging in again.";
      this.user = null; // Ensure user is null on error
    }
  }
};
</script>
