<!DOCTYPE html>
<html>
<head>
    <script src="js/ChoiceSite.js" defer></script>
    <title>Results</title>
    <link rel="stylesheet" href="styles/StyleTheChoice.css"/>
</head>
<body>
<div>
    <h1>Rate the songs</h1>
    <p id="exText">Below is a suggestion based on your choices on the previous page. Choose whether you like
        the song or not. If you do not know the song, go listen to it now or press the "Skip song" button.
        <br>
        Please be aware that the selection of song recommendations is limited, and that skipping songs too many times
        will lead to previously skipped songs reappearing. We recommend that you go listen to the songs you do not know if you have
        the time.

        You will be asked to rate 10 songs in total. When you have rated them all, please press Finish Test
    </p>
    <section id="song">
        <p id="song">

        </p>
    </section>
    <section id="buttons">
        <button id="btn" value="Like" name="like">Love</button>
        <button id="okaybtn" value="Okay" name="okay">Like</button>
        <button id="btn2" value="Don't like" name="don't like">Dislike</button>
        <button id="btn3" value="Don't know" name="don't know">Skip song</button>
    </section>
    <form id="result-form" method="post" action="{{route('result')}}">
        @csrf
        <textarea id="result-text" name="result">0</textarea>
        <textarea id="skip-counter" name="skipCounter">0</textarea>
        <textarea id="model-text" name="model">
            @foreach($songs as $song)
                @if($loop->last)
                    {{$song->model}}
                @endif
            @endforeach
        </textarea>
        <button id="finish" type="submit">Finish test</button>
    </form>
</div>
</body>
</html>
