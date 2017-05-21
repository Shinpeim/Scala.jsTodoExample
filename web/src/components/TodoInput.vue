<style scoped>
</style>
<template>
    <div class="row">
        <div class="col s8 offset-s2">
            <div class="card brown lighten-5">
                <div class="card-content brown-text">
                    <div class="row">
                        <div class="input-field col s7">
                            <input id="body-input" type="text" :class="{invalid: !isTodoInputValid}" placeholder="buy milk" v-model="todoInput" @keyup.enter="addTodo">
                            <label data-error="required" class="active" for="body-input">New Todo</label>
                        </div>
                        <div class="input-field col s3">
                            <input id="due-date-input" type="text" :class="{invalid: !isDueDateInputValid}" placeholder="YYYY-MM-DD" v-model="dueDateInput" @keyup.enter="addTodo">
                            <label data-error="YYYY-MM-DD" class="active" for="due-date-input">Due Date</label>
                        </div>
                        <div class="col s2">
                            <a class="btn-floating btn-large waves-effect waves-light brown dark" @click="addTodo"><i class="material-icons">add</i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import {AddTodoCommand} from '../../../target/scala-2.12/scalajstodo-fastopt'

    export default {
        beforeCreate(){
            this.addTodoCommand = new AddTodoCommand;
        },

        data(){
            return {
                todoInput: "",
                dueDateInput: "",
                isTodoInputValid: true,
                isDueDateInputValid: true
            };
        },

        watch: {
            todoInput(v){
                this.addTodoCommand.todoInput = v;
                this.isTodoInputValid = this.addTodoCommand.isTodoInputValid;
            },
            dueDateInput(v){
                this.addTodoCommand.dueDateInput = v;
                this.isDueDateInputValid  = this.addTodoCommand.isDueDateInputValid;
            }
        },

        methods:{
            addTodo(){
                this.addTodoCommand.execute();
            }
        }
    }
</script>
