
alert("By clicking ok, you allow us to save your choices from the test. No personal information will be saved.");



addTrackEvent();
document.querySelector("#start").disabled = true;
let songPool = document.getElementById('songPool');

function addTrackEvent() {
    let add = document.querySelector(".add");
    add.addEventListener("click", e => {
        addTrack();
        console.log("Clicked!");
        console.log(document.getElementById("songPool").getElementsByTagName("li"));
    });
}

function addTrack() {
    let songList = document.getElementById("songList").options;
    let chosenSong = document.getElementById("listForm").value;

            for (let i = 0; i < songList.length; i++) {
                if (chosenSong === songList[i].value){
                    let newSong = document.createElement("li");
                    newSong.append(document.createTextNode(songList[i].value));
                    songPool.append(newSong);
                    if (!startTest()){
                        document.getElementById("songsText").append(songList[i].innerText + ";");
                    } else {
                        document.getElementById("songsText").append(songList[i].innerText);
                    }
                    startTest();
                    break;
                }
            }
}


function startTest(){
    if(songPool.getElementsByTagName("li").length === 10){
        document.querySelector("#start").disabled = false;
        document.querySelector(".add").disabled = true;
        return true;
    } else {
        return false;
    }
}
