<template id="observations-overview">
    <div>
        <h1 class="banner">Observationslog</h1>
        <h1 class = "center">-------------------------</h1>



        <div>
            <ul id = "sortList">
                <li><a :href="`/observations/newobservation/add`">Add observation</a></li>
                <li><a v-if="sorting" :href="`/observations/?sort_by=id`">Sort by observation-ID</a></li>
                <li><a v-if="sorting" :href="`/observations/?sort_by=name`">Sort by alphabetically</a></li>
                <li><a v-if="sorting" :href="`/observations/?sort_by=planet`">Sort by planet</a></li>
                <li><a v-if="sorting" :href="`/observations/?sort_by=species`">Sort by species</a></li>
            </ul>
        </div>


        <ul class="observations-overview-list">
            <li v-for="observation in observations">
                <a :href="`/observations/${observation.name.trim()}`" class="link-to-observation-details">
                    <div class="single-observation-container" >
                        <h1>{{observation.id}} - {{observation.name}}</h1>
                        <h1>{{observation.species.name}}</h1>
                        <img v-if="observation.pictureUrl" class="cover-image-frontpage" v-bind:src="observation.pictureUrl">
                    </div>
                </a>
            </li>
        </ul>
   </div>
</template>
<script>
    Vue.component("observations-overview", {
        template: "#observations-overview",
        data: () => ({
            observations: [],
            sorting: "id",
        }),
        created() {
            var sort = this.$javalin.queryParams["sort_by"];
            if (typeof sort != "undefined")
                this.sorting = sort;
            fetch(`/api/observations/?sort_by=${this.sorting}`)
                .then(res => res.json())
                .then(res => {
                   this.observations = res;
                })
                .catch(() => alert("Error while fetching observations"));

        }
    });
</script>
<style>
    .nav {
        display: flex;
        justify-content: center;
    }

    .banner {
        text-align: center;
        font-size: 100px;
        overflow: hidden;
        margin: 0;
    }

    li{
        list-style-type: none;
    }

    .observations-overview-list{
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

    .observation-overview-list li{
        padding: 10px;
        border: 1px solid white;
        border-radius: 15px;
    }

    .link-to-observation-details{
        width: 400px;
        height:100px;
        text-decoration: none;
        color: black;
    }

    div.single-observation-container{
        overflow: hidden;
        width: 800px;
        background-color: rgba(0,0,0,0.15);
        margin: 0 auto;
        text-align: center;
        border-radius: 10px;
    }

    div.single-observation-container:hover{
        opacity: 1.0;
        overflow: hidden;
        background-color: rgba(0,0,0,0.4);
        -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.40);
        -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.40);
        box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.40);
    }

    img.cover-image-frontpage {
        height: auto;
        width: 100%;
        padding-bottom: 20px;
        max-height: 280px;
    }

    .button {
        height: 80px;
        width: 200px;
        padding: 20px;
        margin: 10px;
        border: 1px solid black;
        border-radius: 15px;
        background: rgba(0,0,0,0.25);
        font-size: 20px;

    }

    button a{
        text-decoration: none;
        font-weight: bold;
    }

    .button:hover{
        border: 2px solid white;
    }

    #sortList{
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        margin-top: 30px;
        margin-bottom: 30px;
        border-radius: 10px;
    }

    #sortList li{
        padding: 20px;
        margin: 10px;
        border: 1px solid white;
        color: white;
        border-radius: 15px;
        background-color: rgba(140, 170, 210, 0.85);

    }

    #sortList li a{
        color: white;
        text-decoration: none;
        font-weight: bold;
    }

    #sortList li:hover{
        border: 2px solid white;
    }

    .center {
        text-align: center;
    }

</style>