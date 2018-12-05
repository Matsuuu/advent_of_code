<?php
ini_set('memory_limit','512M');

$file = fopen("./polymer.txt", "r");
$polymer = fgets($file);


$occurances = 1;
while ($occurances > 0) {
    $occurances = 0;
    foreach (range("a", "z") as $alphabet) {
        if (strpos($polymer, $alphabet . ucfirst($alphabet)) || strpos($polymer, ucfirst($alphabet) . $alphabet)) {
            $occurances++;
            $polymer = str_replace($alphabet . ucfirst($alphabet), "", $polymer);
            $polymer = str_replace(ucfirst($alphabet) . $alphabet, "", $polymer);
        }
    }
}

echo strlen(trim($polymer));

?>