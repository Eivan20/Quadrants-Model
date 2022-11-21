<!DOCTYPE html>
<html>
<head>
    <title>Recommendation Test</title>
    <meta charset="utf-8">
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="styles/StyleTheTest.css"/>
    <script defer src="js/TestSite.js"></script>

</head>

<body>
<h1>Write 10 songs that you like</h1>

    <div>
        <label for="listForm">Input track</label>
        <input list="songList" name="track" id="listForm"/>
        <input hidden list="songList" name="ids" id="songInfo"/>
        <datalist id ="songList">
            @foreach($songs as $song)
                <option value="{{$song->track_name. " by ". $song->artist_individual}}">{{$song->track_name. " by ". $song->artist_individual." _".$song->id}}</option>
            @endforeach
        </datalist>

        <button class="add" type="button">Add</button>
    </div>
    <div>
        <label for = "songPool">Added songs</label>
        <ol id ="songPool">

        </ol>
    </div>
<form id="added-songs" method="post" action="{{route('storeChosenSongs')}}">
    @csrf
        <textarea id="songsText" name="songsText">

        </textarea>
    <button id="start" type="submit" disabled>Next</button>
</form>
</body>

</html>
