package com.bored.util

fun formatMilliSecondsHHmm(milliseconds: Long):String{
return String.format( "%3d:%02d", milliseconds / 3600000,
    ( milliseconds / 60000 ) % 60 )
}