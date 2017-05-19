<template>
    <div>
        <navigation-header></navigation-header>
        <div class="container">
            <todo-input></todo-input>
        </div>
    </div>
</template>

<script>
    import NavigationHeader from './NavigationHeader.vue'
    import TodoInput from './TodoInput.vue'

    export default {
        components: {
            NavigationHeader,
            TodoInput
        }
    }
</script>


