<?php

namespace App\Http\Controllers;

use App\Models\Test_result;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class ResultController extends Controller
{
    function store(){
        $result = new Test_result();
        $result->model = \request('model');
        $result->successrate = \request('result');
        $result->Skip_Counter = \request('skipCounter');
        $result->save();
        return redirect('done');
    }

    function viewChoices(){
        $song = DB::select( 'SELECT * FROM "suggested_songs" ORDER BY id desc fetch first 1 row only;' );
        echo "<p id=\"json\">".json_encode($song)."</p>";
        return view('ChooseSite',['songs' => $song]);
    }

}
