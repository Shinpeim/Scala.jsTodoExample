<template>
    <div class="row">
        <div class="col s8 offset-s2">
            <table>
                <tbody>
                <tr v-for="t in todos">
                    <td>{{t.body}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
    import {TodoQuery} from '../../../target/scala-2.12/scalajstodo-fastopt'

    export default {
        beforeCreate(){
            this.todoQuery= new TodoQuery;
        },

        created(){
            //ひとまず 1秒ずつqueryする
            setInterval(() => {
                this.todos = this.todoQuery.all()
            }, 100);
        },

        data(){
            return {
                todos: this.todoQuery.all()
            };
        }
    }
</script>
