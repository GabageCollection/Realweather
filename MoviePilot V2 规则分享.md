# MoviePilot V2 规则分享			  -by NEST

## 自定义规则									

### filterGlobal

> 全局排除一些不适合入库的，杜比视界，原盘，较新编码

#### 排除：

```
(?i)SubsPlease|日语无字|Up to 21°C|VARYG|TELESYNC|NTb|先行|DV|MiniBD|sGnb|BHYS|DIY原盘|iPad|UPSCALE|AV1|BDMV|RMVB|DVD|vcd|480p|OPUS|DBD
```

### filterMovie

#### 资源体积：

```
0-25000
```

### filterSeries

#### 资源体积：

```
0-102400
```

### AnimeGroup

#### 包含：

```
7³ACG|VCB-Studio
```

### Audience

> 此处可以改为自己喜欢的组

#### 包含：

```
ADE|ADWeb
```

### HHWEB

#### 包含：

```
HHWEB
```

### Crunchyroll

#### 包含：

```
CR|Crunchyroll
```

### Netflix

#### 包含：

```
Netflix|NF
```

### B-Global

#### 包含：

```
B-Global|BG
```

### AMZN

#### 包含：

```
AMZN|Amazon
```

### HQ

#### 包含：

```
HQ|高码|EDR
```

### DDP

#### 包含：

```
DDP
```



## 优先级规则组

> 以下格式为
>
> 规则组名称 - 适用媒体类型 - 适用媒体类型

### 前置过滤 - 通用 - 全部

```
filterGlobal& !BLU & !REMUX & !3D & !DOLBY 
```

### 动画电影 - 电影 - 动画电影

```
 SPECSUB & 4K & BLURAY & H265 > CNSUB & 4K & BLURAY & H265 > CNSUB & 4K & BLURAY > CNSUB & 1080P & BLURAY > CNSUB & 4K > CNSUB & 1080P 
```

### 华语电影

```
 4K & BLURAY & H265 > 1080P & BLURAY > 4K > 1080P 
```

### 外语电影

```
 SPECSUB & 4K & BLURAY & H265 &filterMovie> CNSUB & 4K & BLURAY & H265 &filterMovie> CNSUB & 1080P & BLURAY &filterMovie> CNSUB & 4K &filterMovie> CNSUB & 1080P &filterMovie
```

### 日番

> 注意，此规则非常个人化，自行斟酌使用
>
> 规则思路：
>
> 已出蓝光：动漫压制组优先
>
> 流媒体：Amazon（亚马逊）- Crunchyroll - Netflix（奈飞） - Bilibili东南亚 2160p - Bilibili东南亚 1080p - 其他源
>
> 兜底为无字幕1080p，不需要去掉即可

```
AnimeGroup& CNSUB & BLURAY & 1080P >Audiences& H265 & BLURAY & 1080P >Audiences&AMZN&HHWEB& CNSUB & 1080P >Audiences&Crunchyroll& CNSUB & 1080P >Audiences&Netflix&HHWEB& CNSUB & 1080P >Audiences&B-Global& 4K & CNSUB >Audiences&B-Global& 1080P & CNSUB >Audiences&HHWEB& CNSUB & 1080P > CNSUB & BLURAY & 1080P > 1080P & CNSUB > 1080P 
```

### 国漫

> DDP以及个人喜欢的组优先

```
 4K &Audiences&HHWEB&DDP> 4K &Audiences&HHWEB> 1080P &Audiences&HHWEB> 4K > 1080P > 720P 
```

### 纪录片

```
 4K & BLURAY > 1080P & BLURAY > 4K > 1080P 
```

### 综艺

```
 4K & WEBDL &HHWEB> WEBDL & 1080P &HHWEB> 4K & WEBDL &Audiences> 1080P &Audiences& WEBDL > 1080P 
```

### 国产剧

> 高码率优先，如果不喜欢太大，都加一个filterSeries

```
 4K & WEBDL &HQ> 4K & WEBDL > 4K & WEBDL > 1080P > 720P 
```

### 欧美剧

```
 SPECSUB & 1080P & BLURAY &filterSeries> 1080P & WEBDL & CNSUB &filterSeries> CNSUB &filterSeries
```

### 日韩剧

```
 SPECSUB & 1080P & BLURAY &filterSeries> CNSUB & 1080P &filterSeries> 1080P & CNSUB &filterSeries> CNSUB &filterSeries 
```

### 音乐剧

```
 CNSUB & 4K > CNSUB & 1080P > 4K > 1080P > !720P 
```



# 记得全部都点一次保存！！！！

# 记得全部都点一次保存！！！！

# 记得全部都点一次保存！！！！

# 最后一步去 订阅 - 订阅优先级规则组全选