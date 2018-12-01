<?php

$file = fopen("./freq.txt", "r");
$freq = 0;

while(!feof($file)) {
    $line = fgets($file);
    $freq += $line;
}
echo "End freq is: " + $freq;
fclose($file);

?>