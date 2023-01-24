<template>
  <div id="signup-form-comp">
		<signup-form></signup-form>
  </div>
</template>

<script>
import SignupForm from '@/components/SignupForm.vue'
export default {
	name: 'SignupView',
	components: {
		SignupForm,
	},
}
</script>

<style>
#signup-form-comp {
  height: 800px;
  
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>