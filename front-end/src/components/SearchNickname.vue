<template>
    <main>
        <div class="result-box">
            {{ keyword }}

        </div>
    </main>
  </template>
  
  <script>
  export default {
    name: 'SearchNickname',
    props: {
        keyword: String,
    },
    data() {
      return {

      }
    },
    computed: {
        // search : function() {
            
        // }

    },
    created() {

    },

    methods : {


    }
  }
  </script>
  <style lang="scss">

  </style>