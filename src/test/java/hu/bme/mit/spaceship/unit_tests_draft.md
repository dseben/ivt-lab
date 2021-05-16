# Egységtesztek tervezése

## 1. A FiringMode.SINGLE mód tesztelése

### 1.1. teszteset
**Forgatókönyv**: Az első `TorpedoStore` objektum kerül elsütésre, sikeres a tüzelési kísérlet.
**Teszteset:** `fireTorpedo_Single_Success()`

### 1.2. teszteset
**Forgatókönyv:** Az első `TorpedoStore` objektum kerül elsütésre, a tüzelési kísérlet nem sikeres.
**Teszteset:** `fireTorpedo_Single_Primary_Failure()`

### 1.3. teszteset
**Forgatókönyv:** Az első `TorpedoStore` objektum kerülne elsütésre, de nincs több töltény, így a második `TS` sül el, sikeresen. 
**Teszteset:** `fireTorpedo_Single_Primary_Empty()`

### 1.4. teszteset
**Forgatókönyv:** A második `TorpedoStore` van soron, így az kerül elsütésre, sikeresen.
**Teszteset:** `fireTorpedo_Single_Secondary_Success()` 

## 2. A FiringMode.ALL mód tesztelésre

### 2.1. teszteset
**Forgatókönyv:** Az első `TorpedoStore` sikertelenül sül el, a második pedig üres, így a kísérlet sikertelen. 
**Teszteset:** `fireTorpedo_All_Failure()`
