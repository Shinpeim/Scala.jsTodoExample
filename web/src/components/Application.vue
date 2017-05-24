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

    import {SynchronizeFailed} from '../../../target/scala-2.12/scalajstodo-fastopt'

    export default {
        components: {
            NavigationHeader,
            TodoInput,
            TodoList
        },

        mounted(){
            SynchronizeFailed.subscribe(() => {
                Materialize.toast("サーバーとの同期に失敗しました", 4000)
            });
        }
    }
</script>


