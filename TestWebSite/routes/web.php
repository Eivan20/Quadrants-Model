<?php


use App\Http\Controllers\TestSiteController;

use App\Http\Controllers\ResultController;

use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', [TestSiteController::class, 'loadSongs']);

Route::get('done',function (){
    return view('Done');
});

Route::get('choose', [ResultController::class, 'viewChoices'])->name('result');

Route::post('choose', [ResultController::class, 'store'])->name('storeChoice');

Route::post('/',[TestSiteController::class,'saveChosenSongs'])->name('storeChosenSongs');

