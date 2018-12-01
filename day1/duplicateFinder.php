<?php
ini_set('memory_limit','512M');

$visitedFrequencies = [];
$currentFreq = 0;

$duplicate = readFileAndModifyFrequency($currentFreq, $visitedFrequencies);

echo "The first duplicate is: " . $duplicate;

function readFileAndModifyFrequency($currentFreq, $visitedFrequencies) {
    echo "Array size is: " + sizeof($visitedFrequencies);
    echo "\n";
    $file = fopen("./freq.txt", "r");

    while(!feof($file)) {
        $line = fgets($file);
        $currentFreq += $line;
        
        if (in_array($currentFreq, $visitedFrequencies)) {
            return $currentFreq;
        } else {
            array_push($visitedFrequencies, $currentFreq);
        }
    }
    
    fclose($file);
    return readFileAndModifyFrequency($currentFreq, $visitedFrequencies);
}
?>