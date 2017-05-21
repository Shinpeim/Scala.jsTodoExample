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

    import {AddTodoRequestFailed, UpdateTodoRequestFailed} from '../../../target/scala-2.12/scalajstodo-fastopt'

    export default {
        components: {
            NavigationHeader,
            TodoInput,
            TodoList
        },

        mounted(){
            AddTodoRequestFailed.subscribe(() => {
                Materialize.toast("todoの作成に失敗しました", 4000)
            });

            UpdateTodoRequestFailed.subscribe(() => {
                Materialize.toast("todoの更新に失敗しました", 4000)
            });
        }
    }
</script>


