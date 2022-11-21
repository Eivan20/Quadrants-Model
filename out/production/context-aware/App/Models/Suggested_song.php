<?php

namespace App\Models;


use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * @property int $id
 * @property string[] $songs
 * @property string[] $artists
 * @property string $model
 */
class Suggested_song extends Model
{
    use HasFactory;

}
