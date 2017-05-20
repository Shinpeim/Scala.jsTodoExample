<style>
    .overdue{
        background-color: coral;
    }
</style>
<template>
    <div class="row">
        <div class="col s8 offset-s2">
            <table>
                <thead>
                <tr>
                    <th>Boby</th>
                    <td>Due Date</td>
                    <td>Done</td>
                </tr>
                </thead>
                <tbody>
                <tr v-for="t in todos" :class="{overdue: t.isOverdue}">
                    <td>{{t.body}}</td>
                    <td>{{t.dueDate}}</td>
                    <td>
                        <span v-if="t.done">done</span>
                        <a class="btn brown" v-else><i class="material-icons" @click="makeDone(t.id)">done</i></a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
    import {TodoQuery, MakeTodoDoneCommand, TodoRepositoryChanged} from '../../../target/scala-2.12/scalajstodo-fastopt'

    export default {
        beforeCreate(){
            this.todoQuery= new TodoQuery;
        },

        created(){
            this.subscription = TodoRepositoryChanged.subscribe(() => {
                console.log("@@@@@@@@@@@@@@@");
                this.todos = this.todoQuery.all()
            }, 100);
        },

        beforeDestroy(){
            this.subscription.unsubscribe();
        },

        methods:{
            makeDone(id){
                const command = new MakeTodoDoneCommand();
                command.execute(id);
            }
        },

        data(){
            return {
                todos: this.todoQuery.all()
            };
        }
    }
</script>
