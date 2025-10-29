# ===========================================================
# Generar árbol visual ASCII del proyecto en estructura_absoluta.txt
# ===========================================================

$output = "estructura_absoluta.txt"
$root = Get-Location

"ESTRUCTURA DE PROYECTO: $root" | Out-File $output
"Fecha: $(Get-Date)" | Out-File $output -Append
"------------------------------------------------------------" | Out-File $output -Append
"" | Out-File $output -Append

function Show-Tree($path, $prefix = "") {
    $items = Get-ChildItem -Force -LiteralPath $path | Sort-Object PSIsContainer, Name
    $count = $items.Count
    for ($i = 0; $i -lt $count; $i++) {
        $item = $items[$i]
        if ($i -eq $count - 1) {
            $connector = "\--- "
        } else {
            $connector = "+--- "
        }

        if ($item.PSIsContainer) {
            "$prefix$connector[DIR] $($item.Name)" | Out-File $output -Append
            if ($i -eq $count - 1) {
                $newPrefix = "$prefix    "
            } else {
                $newPrefix = "$prefix|   "
            }
            Show-Tree $item.FullName $newPrefix
        } else {
            "$prefix$connector$file $($item.Name)" | Out-File $output -Append
        }
    }
}

Show-Tree $root
"" | Out-File $output -Append
"------------------------------------------------------------" | Out-File $output -Append
"Árbol guardado en $output" | Out-File $output -Append

Write-Host "`nEstructura guardada en $output" -ForegroundColor Green
Write-Host "Presiona Enter para salir..."
Read-Host
