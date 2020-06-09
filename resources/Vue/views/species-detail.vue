<template id="species-detail">
   <div class ="all-content-wrapper">
       <div class="margin"></div>
    <div class="content-wrapper">

        <div v-if="observation" class="detail-observation-container">
            <h1>Species Info</h1>

            <img v-bind:src="observation.pictureUrl" class="imgage"/>

        </div>

        <div class="detail-observation-container-info">

            <h1>
                {{species.name}} -- {{species.scientificName}}
            </h1>

            <p>First discovered: {{observation.dateOfObservation.month}} - {{observation.dateOfObservation.year}}
                on the planet "{{observation.location.planet}}".
            </p>

                 <div v-if="species.kindOfSpecies == 'bird'">

                <p>
                Can fly:
                <span v-if="species.canFly == true"> yes</span>
                <span v-else> no</span>
                </p>

                <p>
                    Is a predator:
                    <span v-if="species.predator == true"> yes</span>
                    <span v-else> no</span>
                </p>

                <p>Wingspan: {{species.wingspan}}</p>
                <p>Weight: {{species.weight}}</p>

            </div>

            <div v-if="species.kindOfSpecies == 'amphibian'">

                <p>
                    Number of legs: {{species.numberOfLegs}}
                </p>

                <p>
                    Is poisonous:
                    <span v-if="species.poisonous == true"> yes</span>
                    <span v-else> no</span>
                </p>
            </div>

            <div v-if="species.kindOfSpecies == 'invertebrate'">

                <p>
                    Can fly:
                    <span v-if="species.canFly == true"> yes</span>
                    <span v-else> no</span>
                </p>

                <p>
                    Is poisonous:
                    <span v-if="species.poisonous == true"> yes</span>
                    <span v-else> no</span>
                </p>

                <p>
                    Can breathe under water:
                    <span v-if="species.canBreathUnderWater == true"> yes</span>
                    <span v-else> no</span>
                </p>

                <p>Number of legs: {{species.numberOfLegs}}</p>
                <p>Number of eyes: {{species.numberOfEyes}}</p>

            </div>

            <p>
                Observations so far made in these biomes:

                <br /><br />

                <span class="underline" v-for="biom in bioms">{{biom}}. </span>
            </p>

        </div>
    </div>
   </div>
</template>
<script>
    Vue.component("species-detail", {
        template: "#species-detail",
        data: () => ({
            observation: null,
            species: null,
            bioms: [],
        }),
        created() {
            const observationId = this.$javalin.pathParams["observation-id"];
            console.log(observationId);
            const speciesId = this.$javalin.pathParams["species-id"];
            console.log(speciesId);

            fetch(`/api/observations/${observationId}`)
                .then(res => res.json())
                .then(res => {
                    this.observation = res;
                    this.bioms = this.observation.location.bioms;
                })
                .catch(() => alert("Error while fetching observation"));

            fetch(`/api/observations/${observationId}/species/${speciesId}`)
                .then(res => res.json())
                .then(res => this.species = res)
                .catch(() => alert("Error while fetching species"));
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

    .all-content-wrapper {
        display: flex;
        flex-direction: column;
        justify-items: center;
    }
    .margin {
        margin: 50px;
    }


</style>