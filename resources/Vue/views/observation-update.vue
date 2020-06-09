<template id="observation-update"  @observation-submitted="addObservation">
    <div class="wrap">
    <div class="form-style">
        <h2>Add new observation</h2>
        <form class="create" @submit="checkForm" :action=`/api/observations/${observationName}/update` method="post">
            <div v-if="errors.length">
                <b>Please correct the following error(s):</b>
                <ul>
                    <li v-for="error in errors">{{ error }}</li>
                </ul>
            </div>

            <p>
                <label for="id">Log ID</label>
                <input type="number" name="id" id="id" v-model="observation.id">
            </p>

            <p>
                <label for="name">Log name<label>
                    <input type="text" name="name" id="name" v-model="observation.name">
            </p>

            <p>
                <label for="numberOfSpecimen">Number of specimen<label>
                    <input type="number" name="numberOfSpecimen" id="numberOfSpecimen" v-model="observation.numberOfSpecimen">
            </p>

            <p>
                <label for="dateOfObservation">Date (dd/MM/yyyy)<label>
                    <input type="text" name="dateOfObservation" id="dateOfObservation" v-model="observation.dateOfObservation">
            </p>
            <p>
                <label for="notes">Additional notes<label>
                    <input type="text" name="notes" id="notes" v-model="observation.notes">
            </p>
            <p>
                <label for="pictureUrl">Picture URL<label>
                    <input type="text" name="pictureUrl" id="pictureUrl" v-model="observation.pictureUrl">
            </p>


            <br />

            <h2>Species
                <select v-model="selectedSpecies" name="selectedSpecies">
                    <option value="amphibian">Amphibian</option>
                    <option value="invertebrate">Invertebrate</option>
                    <option value="bird">Bird</option>
                </select>
            </h2>

            <p>
                <label for="speciesName">Name<label>
                    <input type="text" name="speciesName" id="speciesName" v-model="observation.species.name">
            </p>
            <p>
                <label for="speciesScientificName">Scientific name<label>
                    <input type="text" name="speciesScientificName" id="speciesScientificName" v-model="observation.species.scientificName">
            </p>


            <div v-if="selectedSpecies == 'amphibian'">
                <p>
                    <label for="numberOfLegs">Number of legs<label>
                        <input type="number" name="numberOfLegs" id="numberOfLegs" v-model="observation.species.numberOfLegs">
                </p>
                <p>
                    <label for="isPoisonous">Poisnous. true/false<label>
                        <input type="text" name="isPoisonous" id="isPoisonous" v-model="observation.species.isPoisonous">
                </p>
            </div>



            <div v-else-if="selectedSpecies == 'invertebrate'">
                <p>
                    <label for="canFly">Can fly? true/false<label>
                        <input type="text" name="canFly" id="canFly" v-model="observation.species.canFly">
                </p>
                <p>
                    <label for="isPoisonous">Poisnous. true/false<label>
                        <input type="text" name="isPoisonous" id="isPoisonous" v-model="observation.species.isPoisonous">
                </p>
                <p>
                    <label for="canBreathUnderWater">Can breath under water? true/false<label>
                        <input type="text" name="canBreathUnderWater" id="canBreathUnderWater" v-model="observation.species.canBreathUnderWater">
                </p>
                <p>
                    <label for="numberOfLegs">Number of legs<label>
                        <input type="number" name="numberOfLegs" id="numberOfLegs" v-model="observation.species.numberOfLegs">
                </p>
                <p>
                    <label for="numberOfEyes">Number of eyes<label>
                        <input type="number" name="numberOfEyes" id="numberOfEyes" v-model="observation.species.numberOfEyes">
                </p>
            </div>


            <div v-else-if="selectedSpecies == 'bird'">
                <p>
                    <label for="canFly">Can fly? true/false<label>
                        <input type="text" name="canFly" id="canFly" v-model="observation.species.canFly">
                </p>
                <p>
                    <label for="isPredator">Predator? true/false<label>
                        <input type="text" name="isPredator" id="isPredator" v-model="observation.species.isPredator">
                </p>
                <p>
                    <label for="wingspan">Wingspan<label>
                        <input type="number" step="0.000001" name="wingspan" id="wingspan" v-model="observation.species.wingspan">
                </p>
                <p>
                    <label for="weight">Number of legs<label>
                        <input type="number" step="0.000001" name="weight" id="weight" v-model="observation.species.numberOfLegs">
                </p>

            </div>

            <br />

            <div>
                <h2>Location</h2>

                <p>
                    <label for="locationName">Name<label>
                        <input type="text" name="locationName" id="locationName" v-model="observation.location.name">
                </p>
                <p>
                    <label for="planet">Planet<label>
                        <input type="text" name="planet" id="planet" v-model="observation.location.planet">
                </p>
                <p>
                    <label for="longitude">Longitude<label>
                        <input type="number" step="0.000001" name="longitude" id="longitude" v-model="observation.location.longitude">
                </p>
                <p>
                    <label for="latitude">Latitude<label>
                        <input type="number" step="0.000001" name="latitude" id="latitude" v-model="observation.location.latitude">
                </p>

                <p>
                    <label for="biom">Biom separate with comma (biom1,biom2,etc)<label>
                        <input type="text" name="biom" id="biom" v-model="observation.location.biom">
                </p>

            </div>

            <p>
                <input type="submit" value="Update Observation">
            </p>

        </form>
    </div>
    </div>

</template>
<script>
    Vue.component("observation-update", {
        template: "#observation-update",
        data: () => ({
            observation: null,
            observationName: null,
            selectedSpecies: null,
            errors: [],
        }),
        created() {
            const observationId = this.$javalin.pathParams["observation-id"];

            if (observationId != null) {
                fetch(`/api/observations/${observationId}`)
                    .then(res => res.json())
                    .then(res => this.observation = res)
                    .then(res => this.observationName = this.observation.name)
                    .catch(() => alert("Error while fetching planet"));
            }
        },
        methods:{
            checkForm:function(e) {
                const urlRegex = "/^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\\.(?:[a-z\u00a1-\uffff]{2,})))(?::\\d{2,5})?(?:[/?#]\\S*)?$/i";
                if(this.observationName) return true;
                this.errors = [];
                if (!this.observationName) this.errors.push("Name required");
                e.preventDefault();
            }
        }
    });
</script>
<style>
    .wrap {
        display: flex;
        flex-direction: row;
    }


    .form-style{
        font-family: 'Open Sans Condensed', arial, sans;
        width: 500px;
        padding: 30px;
        background: rgba(0,0,0,0.45);
        box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.22);
        -moz-box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.22);
        -webkit-box-shadow:  0px 0px 15px rgba(0, 0, 0, 0.22);
        border: #dddddd;
    }
    .form-style h2{
        background: rgba(50,45,50,0.65);
        text-transform: uppercase;
        font-family: 'Open Sans Condensed', sans-serif;
        color: #FFFFFF;
        font-size: 18px;
        font-weight: 100;
        padding: 20px;
        margin: -30px -30px 30px -30px;
    }
    .form-style input[type="text"],
    .form-style input[type="date"],
    .form-style input[type="datetime"],
    .form-style input[type="email"],
    .form-style input[type="number"],
    .form-style input[type="search"],
    .form-style input[type="time"],
    .form-style input[type="url"],
    .form-style input[type="password"],
    .form-style textarea,
    .form-style select
    {
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        outline: none;
        display: block;
        width: 100%;
        padding: 7px;
        border: none;
        color: white;
        border-bottom: 1px solid #ddd;
        background: transparent;
        margin-bottom: 10px;
        font: 16px Arial, Helvetica, sans-serif;
        height: 45px;
    }
    .form-style textarea{
        resize:none;
        overflow: hidden;
    }
    .form-style input[type="button"],
    .form-style input[type="submit"]{
        background: none;
        display: inline-block;
        cursor: pointer;
        font-family: 'Open Sans Condensed', sans-serif;
        font-size: 14px;
        text-decoration: none;
        text-transform: uppercase;
        padding: 10px;
        margin: 10px;
        border: 1px solid white;
        color: white;
        border-radius: 15px;
    }
    .form-style input[type="button"]:hover,
    .form-style input[type="submit"]:hover {
        border: 2px solid white;
    }

    .create {
        color: white;
    }
</style>
