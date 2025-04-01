# Farm(Fruit AR Vision)


<img src="https://github.com/kimykstar/Farm/assets/91407685/b6170812-bc5d-43c7-9a01-8f57b60525a9" width="300px" height="300px"/>


## 프로젝트 개요

> 카메라 촬영 혹은 이미지를 업로드해 어떤 과일인지 알아보세요!

AI를 통한 과일 판별, 신선도 등의 정보를 제공합니다. 또한, AR을 통한 과일의 단면 정보를 제공해 
과일을 직접 구매하지 않고도 과일의 단면 정보를 볼 수 있습니다.



개발 기간 : 2023-04 ~ 2023-11

### 프로젝트 목적

코로나 19로 인해 건강 관리의 관심이 증대되고, 국내 과일 시장이 점차 감소하며 수입 과일, 개량 품종 과일 등 
다양한 과일들이 등장하고 있습니다. 이에 국내 과일 시장 활성화 및 과일을 통한 현대인들의 건강 관리를 
촉진시키고, 수입 과일, 개량 품종과일 등 잘 모르는 과일들에 대한 정보를 효율적으로 제공하기를 목적으로 합니다.

## 아키텍처

![Image](https://github.com/user-attachments/assets/8746074d-c1a6-41b3-97bd-3d0695d082f2)

<br/>

## 주요 기능

### 1. 과일 정보 제공

> 이미지 기반 과일의 상함정도 분석 및 과일을 판별해 과일의 영양소 및 효능 정보를 제공합니다. 

![Image](https://github.com/user-attachments/assets/69a40d92-5af6-4f60-aa2f-a9055ada6860)
![Image](https://github.com/user-attachments/assets/98e6fef2-fadd-4390-9293-97a805232397)
![Image](https://github.com/user-attachments/assets/2aacaa74-1791-408d-ba49-b786cc9a8491)

### 2. 과일 증강 이미지

> 과일 단면 AR 이미지를 제공합니다.

![](https://github.com/user-attachments/assets/280003fe-a17d-4309-a8eb-782de0bbd981) 
![](https://github.com/user-attachments/assets/ee452865-5c4e-478a-873f-9b405d6acc3d)


### 3. 과일 추천

> 제철 과일 혹은 사용자가 관심있어 하는 영양소에 따른 과일 추천 기능 제공

![Image](https://github.com/user-attachments/assets/1ceefd21-4825-4f95-af1d-378baff9ab5d)
![Image](https://github.com/user-attachments/assets/f3324aca-285b-423e-abc3-09f683731a8a)

## 기술적 도전

### AI판별 시간 문제

AI서버를 따로 구축해 Socket통신으로 받은 이미지 데이터를 통해 과일을 판별하는데 시간이 10초 이상 걸리는 문제를 
개선하고자, 경량화 모델인 TensorFlow Lite를 활용해 Android 자체에 내장하는 방식으로 구조를 개선했습니다.

결과적으로, AI를 통해 과일 판별을 위해 7초 가량 단축했습니다.

<b>기존</b>

![Image](https://github.com/user-attachments/assets/e6bf9804-b6dc-43cf-a373-2d0b7a239af9)

<b>개선</b>


![Image](https://github.com/user-attachments/assets/8746074d-c1a6-41b3-97bd-3d0695d082f2)

### AR 기술 활용

과일 단면 정보를 제공하기 위한 AR기술을 활용하기 위해 기존에는 Unity를 활용하려 했으나, Android와 연동의
 불편함과 성능이 좋지 않음을 인지했습니다. 이에 구글에서 제공하는 ARCore를 활용해 과일 단면 증강 이미지를 제공합니다.

증강 이미지 파일은 Blendar를 활용해 직접 모델링해 `.gltf`파일을 만들었습니다.

https://github.com/user-attachments/assets/d26791a1-65fb-4af6-b4d8-3d014bfe6453