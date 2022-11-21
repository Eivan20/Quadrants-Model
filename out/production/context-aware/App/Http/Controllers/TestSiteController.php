<?php

namespace App\Http\Controllers;

use App\Models\Added_song;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class TestSiteController extends Controller
{
    //load songs from DB into datalist
    public function loadSongs(){
        $song = DB::select( 'select distinct on (track_name) * from final;' );
        return view('TestSite', ['songs' => $song]);
    }

    //save songs added to list by user
    public function saveChosenSongs(){
        $result = new Added_song();
        $result->songs = \request('songsText');
        $result->save();
        return redirect()->route('result');
    }
}
