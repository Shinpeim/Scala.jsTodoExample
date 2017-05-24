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
                    <td>Syncronizing</td>
                </tr>
                </thead>
                <tbody>
                <tr v-for="t in todos" :class="{overdue: t.isOverdue}">
                    <td>
                        {{t.body}}
                    </td>
                    <td>{{t.dueDate}}</td>
                    <td>
                        <span v-if="t.done">done</span>
                        <a v-else class="btn brown" @click="makeDone(t.id)"><i class="material-icons">done</i></a>
                    </td>
                    <td>
                        <div class="preloader-wrapper small active" v-if="t.isSynchronizing">
                            <div class="spinner-layer spinner-brown-only">
                                <div class="circle-clipper left">
                                    <div class="circle"></div>
                                </div>
                                <div class="gap-patch">
                                    <div class="circle"></div>
                                </div>
                                <div class="circle-clipper right">
                                    <div class="circle"></div>
                                </div>
                            </div>
                        </div>
                        <div v-if="t.isSynchronizeNeeded">
                            <a class="btn brown" @click="reSync(t.id)"><i class="material-icons">replay</i></a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>
<script>
    import {
        TodoQuery,
        MakeTodoDoneCommand,
        ResynchronizeTodoCommand,
        TodoRepositoryChanged
    } from '../../../target/scala-2.12/scalajstodo-fastopt'

    export default {
        beforeCreate(){
            this.todoQuery= new TodoQuery;
        },

        created(){
            this.subscription = TodoRepositoryChanged.subscribe(() => {
                this.todos = this.todoQuery.all();
                console.log(this.todos);
            });
        },

        beforeDestroy(){
            this.subscription.unsubscribe();
        },

        methods:{
            makeDone(id){
                const command = new MakeTodoDoneCommand();
                command.execute(id);
            },
            reSync(id){
                const command = new ResynchronizeTodoCommand();
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
