const song = document.getElementById('song');
const choices = (JSON.parse(document.getElementById("json").innerText));
const songArray = choices[0].songs.split(",");
for (let i = 0; i < songArray.length; i++) {
    songArray[i] = songArray[i].replace('{', '')
    songArray[i] = songArray[i].replace('}', '')
    songArray[i] = songArray[i].replace('\[', '')
    songArray[i] = songArray[i].replace('\]', '')
    songArray[i] = songArray[i].replace('"', '')
    songArray[i] = songArray[i].replace('\"', '')
}
const artistArray = choices[0].artists.split(",");
for (let i = 0; i < artistArray.length; i++) {
    artistArray[i] = artistArray[i].replace('{', '')
    artistArray[i] = artistArray[i].replace('}', '')
    artistArray[i] = artistArray[i].replace('\[', '')
    artistArray[i] = artistArray[i].replace('\]', '')
    artistArray[i] = artistArray[i].replace('"', '')
    artistArray[i] = artistArray[i].replace('\"', '')

}

//["Hello - Adele", "Pierre er min gud - Eibert","September - EWF"];
const b1 = document.getElementById("btn");
const b2 = document.getElementById("btn2");
const b3 = document.getElementById("btn3");
const bokay = document.getElementById("okaybtn");
const finish = document.getElementById("finish");
let count = 0;
let counter = choices.length - 1;
let like = 0;
let total = 10;
let skipCounter = 0;

b1.addEventListener("click", saveLike);
bokay.addEventListener("click", saveOkay);
b2.addEventListener("click", saveDislike);
b3.addEventListener("click", skipSong);
console.log(songArray);
nextSong();

function saveLike() {
    console.log(song.innerText + " liked")
    like++;
    document.getElementById("result-text").innerHTML = like / total;
    count++;
    console.log(count)
    endCheck();
}

function saveOkay() {
    like += 0.5;
    document.getElementById("result-text").innerHTML = like / total;
    count++;
    endCheck();
}

function saveDislike() {
    console.log(song.innerText + " disliked")
    count++;
    console.log(count)
    endCheck();
}

function skipSong() {
    skipCounter++;
    document.getElementById("skip-counter").innerHTML = skipCounter;
    console.log(skipCounter);
    nextSong();
}


function nextSong() {
    //get from generated list of possible recommendation
    const next = songArray[counter] + " by " + artistArray[counter];
    //choices[0].songs;
    //.songs[counter] +" by "+ choices[0].artists[counter];
    //console.log("Next: "+next)
    //console.log("SongArray.lenght: "+songArray.length)
    counter++;
    //Something goes wrong with the counter here...
    if (counter === songArray.length) {
        counter = 0;
    }
    song.innerText = next;
}

function endCheck() {
    if (count === total) {
        b1.style.display = "none";
        b2.style.display = "none";
        b3.style.display = "none";
        bokay.style.display = "none";
        finish.style.visibility = "visible"
        window.onbeforeunload = function () {
            alert('Please make sure you have submitted your choices by pressing Finish Test before leaving')
        }
    } else {
        nextSong();
    }
}





