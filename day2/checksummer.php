<?php
ini_set('memory_limit','512M');

$file = fopen("./boxes.txt", "r");

$threes = 0;
$twos = 0;

while(!feof($file)) {
    $word = fgets($file);
    $sorted = wordSorter($word);
    if (in_array(2, $sorted)) {
        $twos++;
    }
    if (in_array(3, $sorted)) {
        $threes++;
    }   
}

echo "Word: " . $word . "\n";
echo "Twos: " . $twos . "\n";
echo "Threes: " . $threes . "\n";
echo "Checksum is: " . ($twos * $threes);

function wordSorter($word) {
    $wordArr = [];
    foreach(str_split($word) as $letter) {
        if (array_key_exists($letter, $wordArr)) {
            $wordArr[$letter] += 1;
        } else {
            $wordArr[$letter] = 1;
        }
    }
    arsort($wordArr);
    return $wordArr;
}

?>