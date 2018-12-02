<?php

ini_set('memory_limit','512M');

$file = fopen("./boxes.txt", "r");

$wordArr = [];

while(!feof($file)) {
    array_push($wordArr, fgets($file));
}

$rightBoxes = [];
foreach($wordArr as $word) {
    foreach($wordArr as $w) {
        $diff = compareNames($word, $w);
        if ($diff == 1) {

            array_push($rightBoxes, $w);
            array_push($rightBoxes, $word);
            break;
        }
    }
    if (!empty($rightBoxes)) {
        break;
    }
}
echo "Right box names are: \n";
echo $rightBoxes[0] . "\n";
echo $rightBoxes[1];

function compareNames($name1, $name2) {
    $name1Arr = str_split($name1);
    $name2Arr = str_split($name2);
    $diffCount = 0;

    for($i = 0; $i < count($name1Arr); $i++) {
        if ($name1Arr[$i] != $name2Arr[$i]) {
            $diffCount++;
        }
    }
    return $diffCount;
}

?>