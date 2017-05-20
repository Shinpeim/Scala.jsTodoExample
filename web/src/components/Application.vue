<template>
    <div>
        <navigation-header></navigation-header>
        <div class="container">
            <todo-input></todo-input>
            <todo-list></todo-list>
        </div>
    </div>
</template>

<script>
    import NavigationHeader from './NavigationHeader.vue'
    import TodoInput from './TodoInput.vue'
    import TodoList from './TodoList.vue'

    export default {
        components: {
            NavigationHeader,
            TodoInput,
            TodoList
        }
    }
</script>


