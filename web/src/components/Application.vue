<template>
    <div>Application of {{mew}}</div>
</template>

<script>
    import {Nyan} from '../../../target/scala-2.12/scalajstodo-fastopt'

    export default {
        data(){
            return {
                mew: (new Nyan()).mew()
            }
        }
    }
</script>


