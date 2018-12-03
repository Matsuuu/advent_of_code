<?php

$fabric = [];
for($i = 0; $i <= 1000; $i++) {
    $fabric[$i] = [];
    for ($j = 0; $j <= 1000; $j++) {
        $fabric[$i][$j] = 0;
    }
}

$file = fopen("./claims.txt", "r");

while (!feof($file)) {
    $claim = fgets($file);
    $claimObj = getClaimAsArray($claim);

    $xStartPoint = $claimObj["coords"]["x"];
    $xLength = $claimObj["dimensions"]["x"];

    $yStartPoint = $claimObj["coords"]["y"];
    $yLength = $claimObj["dimensions"]["y"];

    for($i = $xStartPoint; $i < $xStartPoint + $xLength; $i++) {
        for ($j = $yStartPoint; $j < $yStartPoint + $yLength; $j++) {
            $fabric[$i][$j] += 1;
        }
    }
}
fclose($file);

$overLaps = 0;

for($i = 0; $i <= 1000; $i++) {
    for ($j = 0; $j <= 1000; $j++) {
        if ($fabric[$i][$j] > 1) {
            $overLaps++;
        }
    }
}

$file = fopen("./claims.txt", "r");

while (!feof($file)) {
    $claim = fgets($file);
    $claimObj = getClaimAsArray($claim);
    
    doesOverlap($claimObj, $fabric);
}



function getClaimAsArray($claim) {
    $splitClaim = preg_split("/(@|:)/", $claim);

    $coordinates = trim($splitClaim[1]);
    $splitCoords = explode(",", $coordinates);

    $dimensions = trim($splitClaim[2]);
    $splitDimensions = explode("x", $dimensions);

    $claimObj = [];

    $claimObj["id"] = trim($splitClaim[0]);
    $claimObj["coords"] = [];
    $claimObj["coords"]["x"] = $splitCoords[0];
    $claimObj["coords"]["y"] = $splitCoords[1];
    $claimObj["dimensions"] = [];
    $claimObj["dimensions"]["x"] = $splitDimensions[0];
    $claimObj["dimensions"]["y"] = $splitDimensions[1];

    return $claimObj;
}

function doesOverlap($claimObj, $fabric) {
    $overlaps = false;

    $xStartPoint = $claimObj["coords"]["x"];
    $xLength = $claimObj["dimensions"]["x"];

    $yStartPoint = $claimObj["coords"]["y"];
    $yLength = $claimObj["dimensions"]["y"];

    for($i = $xStartPoint; $i < $xStartPoint + $xLength; $i++) {
        for ($j = $yStartPoint; $j < $yStartPoint + $yLength; $j++) {
            if ($fabric[$i][$j] > 1) {
                $overlaps = true;
            }
        }
    }

    if ($overlaps == false) {
        print_r($claimObj);
    }
}

?>  