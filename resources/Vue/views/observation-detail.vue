<template id="observation-detail">
    <div class="content-wrapper">

    <div class="detail-observation-container">
        {{console.log(this.observation)}}
        <h1>{{observation.name}}</h1>


        <a v-if="observation" class="link-to-observation-details" :href="`/observations/${observation.name}/species/${observation.species.name}`">
            <img v-bind:src="observation.pictureUrl" class="imgage"/>
        </a>
    </div>

    <div class="detail-observation-container-info">
        <h1>Info</h1>

        <p>First discovered: {{observation.dateOfObservation.month}} - {{observation.dateOfObservation.year}}
            on the planet "{{observation.location.planet}}" and is a(n) {{observation.species.type}}.
        </p>

        <p>
            We found {{observation.numberOfSpecimen}} of theese running around in {{observation.location.name}}.
            We call them "{{observation.species.name}}".
        </p>
        <p>
            Observations so far made in these biomes:

            <br />

            <span class="underline" v-for="biom in bioms">{{biom}}. </span>
        </p>



        <br /><br /><br /><br />

        <a class="button" :href="`/api/observations/${observation.name}/delete`">Delete</a>
        <a class="button" :href="`/observations/${observation.name}/update`">Edit</a>

    </div>

    <div class="notes-container">
        <h1>Notes</h1>
        <p>{{observation.notes}}</p>
    </div>

</template>
<script>
    Vue.component("observation-detail", {
        template: "#observation-detail",
        data: () => ({
            observation: null,
            bioms: [],
        }),
        created() {
            const observationId = this.$javalin.pathParams["observation-id"];
            console.log(observationId);
            fetch(`/api/observations/${observationId}`)
                .then(res => res.json())
                .then(res => {
                    this.observation = res
                    this.bioms = this.observation.location.bioms
                })
                .catch(() => alert("Error while fetching observation"));
        }
    });
</script>
<style>

    .content-wrapper {
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
    }
    img.list-image {
        width: 140px;
        padding-bottom: 20px;
        transition: transform .2s;
    }

    .list-image:hover {
        transform: scale(1.1);
    }


    ul{
       color:white;
    }
    div.detail-observation-container > p {
        max-width: 30em;
    }
    div.detail-observation-container{
        padding-top: 10px;
        overflow: hidden;
        width: 48%;
        height: 22em;
        background-color: rgba(0, 0, 0, 0.3);
        margin: 0 auto;
        opacity: 0.96;
        text-align: center;
        border-radius: 30px;
        font-size: 20px;
        -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
    }

    div.detail-observation-container-info{
        padding-top: 10px;
        overflow: hidden;
        width: 48%;
        height: 22em;
        background-color: rgba(255, 255, 255, 0.7);
        margin: 0 auto;
        opacity: 0.96;
        text-align: center;
        border-radius: 30px;
        font-size: 20px;
        -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
    }

    div.notes-container{
        margin-top: 20px;
        padding-top: 10px;
        overflow: hidden;
        width: 100%;
        background-color: rgba(255, 255, 200    , 0.7);
        opacity: 0.96;
        text-align: center;
        border-radius: 30px;
        font-size: 20px;
        -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
    }


   img {
       border-radius: 30px;
       max-width: 500px;
       max-height: 300px;
   }

    .detail-observation-container h1 {
        font-size: 42px;
    }

    .detail-observation-container p {
        font-size: 22px;
    }

    .button {
        padding: 20px;
        margin: 10px;
        border: 1px solid white;
        color: black;
        border-radius: 15px;
        background-color: rgba(140, 170, 210, 0.5);
    }

    button a{
        color: white;
        text-decoration: none;
        font-weight: bold;
    }

    .button:hover{
        border: 2px solid white;
    }
    .underline {
        text-decoration: underline;
    }


</style>